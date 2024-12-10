export default function BlogList({title, blogs, onDelete}) {
    return (
        <div className="blog-list">
            <h2>{title}</h2>
            {blogs.map(blog => (
                <div className="blog-preview" key={blog.id}>
                    <h2>{blog.title}</h2>
                    <p>Written by {blog.author}</p>
                    <button type="button" onClick={() => onDelete(blog.id)}>Delete blog</button>
                </div>
            ))}
        </div>
    )
}