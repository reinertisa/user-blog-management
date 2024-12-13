import Navbar from "./components/Navbar";
import Index from "./components/Blog";
import {BrowserRouter,  Route, Routes} from "react-router";
import BlogForm from "./components/Blog/Form";
import BlogDetails from "./components/Blog/Details";

function App() {
  return (
      <div className="App">
          <BrowserRouter>
              <Navbar/>
              <div className="content">
                  <Routes>
                      <Route path="/" element={<Index />} />
                      <Route path="/create" element={<BlogForm />} />
                      <Route path="/blogs/:id" element={<BlogDetails />} />
                  </Routes>
              </div>
          </BrowserRouter>
      </div>
  );
}

export default App;
