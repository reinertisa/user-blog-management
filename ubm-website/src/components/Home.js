import {useState} from "react";
import BlogList from "./BlogList";

export default function Home() {

    const [blogs, setBlogs] = useState([
        {title: 'Student university class project', body: 'Bla Bla Bla...', author: 'Isa Reinert', id: 1},
        {title: 'Blog project management project', body: 'Hey dude', author: 'Sade Miller', id: 2},
        {title: 'Company tech stack project', body: 'This is professional', author: 'Kristi Reinert', id: 3},
    ]);

    return (
        <div className="home">
            <BlogList title="All Blogs" blogs={blogs} />
        </div>
    )
}