import {Link} from "react-router";

export default function Navbar() {
    return (
        <nav className="navbar">
            <Link to="/"><h1>The User Blog Management</h1></Link>
            <div className="links">
                <Link to="/blogs">Blogs</Link>
                <Link to="/createBlog">New Blog</Link>
                <Link to="/authors">Authors</Link>
                <Link to="/createAuthor">New Author</Link>
            </div>
        </nav>
    )
}