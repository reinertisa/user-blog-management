import useFetch from "../../hooks/useFetch";
import AuthorList from "./List";

export default function AuthorPage() {
    const {data: authors, loading, error} = useFetch('http://localhost:8080/api/v1/authors');

    return (
        <div className="home">
            {error && <div>{error}</div>}
            {loading && <div>Loading...</div>}
            {authors && <AuthorList title="All Authors" authors={authors} />}
        </div>
    )
}