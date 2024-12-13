import {Link} from "react-router";

export default function AuthorList({title, authors}) {

    return (
        <div className="author-list">
            <h2>{title}</h2>
            {authors.map(author => (
                <div className="author-preview" key={author.id}>
                    <Link to={`/authors/${author.id}`}>
                        <h2>{author.firstName}{' '}{author.lastName}</h2>
                    </Link>
                </div>
            ))}
        </div>
    );
}