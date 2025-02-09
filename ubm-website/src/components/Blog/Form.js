import {useState, useEffect} from "react";
import {useNavigate} from "react-router";
import useFetch from "../../hooks/useFetch";

export default function BlogForm() {
    const {data: authorNameOptions, loading, error} = useFetch('http://localhost:8080/api/v1/authors/options');
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [author, setAuthor] = useState('reinert.isa@gmail.com');
    const [pending, setPending] = useState(false);
    const navigate = useNavigate();

    const handleTitleChange = (e) => setTitle(e.target.value);
    const handleContentChange = (e) => setContent(e.target.value);
    const handleAuthorChange = (e) => setAuthor(e.target.value);

    const handleSubmit = async (evt) => {
        evt.preventDefault();
        evt.stopPropagation();

        const blog = {
            title,
            content,
            email: author
        };
        setPending(true)
        await fetch('http://localhost:8080/api/v1/blogs', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(blog)
        });
        setPending(false);
        navigate('/blogs');

    }

    let body = <p>Loading....</p>

    if (authorNameOptions?.length > 0) {
        body = (
            <div className="create">
                <h2>Add a New Blog</h2>
                <form onSubmit={handleSubmit}>
                    <label htmlFor="title">Blog title:</label>
                    <input
                        id="title"
                        type="text"
                        value={title}
                        required={true}
                        onChange={handleTitleChange}
                    />

                    <label htmlFor="content">Blog content:</label>
                    <textarea
                        id="content"
                        value={content}
                        onChange={handleContentChange}
                    />

                    <label htmlFor="author">Blog author: </label>
                    <select
                        id="author"
                        value={author}
                        onChange={handleAuthorChange}
                    >
                        {authorNameOptions.map(o => (
                            <option key={`${o.value}--option`} value={o.value}>{o.label}</option>
                        ))}
                    </select>
                    <button type="submit" disabled={pending}>{loading ? 'Adding Blog...' : 'Add Blog'}</button>
                </form>
            </div>
        )
    } else if (error) {
        body = <p>Error happened</p>
    }

    return body;
}