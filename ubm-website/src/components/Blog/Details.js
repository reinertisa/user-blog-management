import {useNavigate, useParams} from "react-router";
import useFetch from "../../hooks/useFetch";

export default function BlogDetails() {
    const navigate = useNavigate();
    const {id} = useParams();
    const {data: blog, loading, error} = useFetch(`http://localhost:8080/api/v1/blogs/${id}`);

    const handleDelete = async () => {
        try {
            await fetch(`http://localhost:8080/api/v1/blogs/${id}`, {
                method: 'DELETE',
            })
            navigate('/blogs');
        } catch (err) {
            console.log(err);
        }
    }

    return (
        <div className="blog-details">
            {loading && <div>Loading..</div>}
            {error && <div>{error}</div>}
            {blog && (
                <article>
                    <h2>{blog.title}</h2>
                    <p>Written by {blog.author}</p>
                    <div>{blog.content}</div>
                    <button type="button" onClick={handleDelete}>Delete</button>
                </article>
            )}
        </div>
    )
}