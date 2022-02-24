import "./App.css";
import React from "react";
import {
  Routes,
  Route
} from "react-router-dom";
import Menu from "./components/menu/menu"
import TaskPage from "./pages/task_page/task_page";
import PerfilPage from "./pages/perfil/perfil";
import LoginRegisterPage from "./pages/login_register_page/login_register_page";

class App extends React.Component {  
  render() {
    return (
      <div className="App">
          <Menu />
          <Routes>
          <Route path="/tasks" element={<TaskPage />} />
          <Route path="/perfil" element={<PerfilPage />} />
          <Route path="/login" element={<LoginRegisterPage />} />
          <Route exact path="/" element={<LoginRegisterPage />} />
          </Routes>
      </div>
    );
  }
}

export default App;
