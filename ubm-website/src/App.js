import Navbar from "./components/Navbar";
import BlogPage from "./components/Blog";
import {BrowserRouter,  Route, Routes} from "react-router";
import BlogForm from "./components/Blog/Form";
import BlogDetails from "./components/Blog/Details";
import AuthorForm from "./components/Author/Form";
import AuthorPage from "./components/Author";

function App() {
  return (
      <div className="App">
          <BrowserRouter>
              <Navbar/>
              <div className="content">
                  <Routes>
                      <Route path="/" element={<div>Home</div>} />
                      <Route path="/blogs" element={<BlogPage />} />
                      <Route path="/createBlog" element={<BlogForm />} />
                      <Route path="/authors" element={<AuthorPage />} />
                      <Route path="/createAuthor" element={<AuthorForm />} />
                      <Route path="/blogs/:id" element={<BlogDetails />} />
                  </Routes>
              </div>
          </BrowserRouter>
      </div>
  );
}

export default App;
