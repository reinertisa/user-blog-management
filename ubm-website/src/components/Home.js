import {useEffect, useState} from "react";
import BlogList from "./BlogList";

export default function Home() {

    const [blogs, setBlogs] = useState(null);

    useEffect(() => {
        const loadData = async () => {
            const rez = await fetch('http://localhost:8080/api/v1/blogs');
            const data = await rez.json();
            setBlogs(data);
        }

        loadData();

    }, []);

    const handleDelete = (id) => {
        setBlogs(blogs.filter(blog => blog?.id !== id));
    }

    return (
        <div className="home">
            {blogs && <BlogList title="All Blogs" blogs={blogs} onDelete={handleDelete}/>}
        </div>
    )
}