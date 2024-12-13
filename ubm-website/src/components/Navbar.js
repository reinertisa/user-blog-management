import {Link} from "react-router";

export default function Navbar() {
    return (
        <nav className="navbar">
            <h1>The User Blog Management</h1>
            <div className="links">
                <Link to="/">Home</Link>
                <Link to="/create">New Blog</Link>
            </div>
        </nav>
    )
}