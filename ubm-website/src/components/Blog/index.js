import List from "./List";
import useFetch from "../../hooks/useFetch";

export default function Index() {
    const {data : blogs, loading, error} = useFetch('http://localhost:8080/api/v1/blogs');

    return (
        <div className="home">
            {error && <div>{error}</div>}
            {loading && <div>Loading...</div>}
            {blogs && <List title="All Blogs" blogs={blogs} />}
        </div>
    )
}