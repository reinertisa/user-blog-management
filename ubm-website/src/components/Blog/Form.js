import {useState} from "react";
import {useNavigate} from "react-router";

export default function BlogForm() {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [author, setAuthor] = useState('reinert.isa@gmail.com');
    const [loading, setLoading] = useState(false);
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
        setLoading(true)
        await fetch('http://localhost:8080/api/v1/blogs', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(blog)
        });
        setLoading(false);
        navigate('/blogs');

    }

    return (
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
                    <option value="reinert.isa@gmail.com">Isa Reinert</option>
                    <option value="sade.miller@gmail.com">Sade Miller</option>
                    <option value="kristi.reinert@gmail.com">Kristi Reinert</option>
                </select>
                <button type="submit" disabled={loading}>{loading ? 'Adding Blog...' : 'Add Blog'}</button>
            </form>
        </div>
    )
}