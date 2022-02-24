import {
  Card,
  Tab,
  Tabs,
  Box,
  CardContent,
  TextField,
  Typography,
  Button,
  Alert,
} from "@mui/material";
import { useState } from "react";
import "./login_register_page.css";
import { useNavigate } from "react-router-dom";
import { logIn, createUser, setCurrentUser } from "../../services/UserService"

const LoginRegisterPage = () => {
  const navigate = useNavigate();
  const [currTab, setCurrTab] = useState(0);

  const handleChangeTabs = (_, newValue) => {
    setCurrTab(newValue);
  };

  const handleLogin = () => {
    navigate("/tasks");
  };

  const handleCadastro = () => {
    window.alert("Faça o login com seus dados cadastrados !")
  };

  return (
    <main>
      <Card sx={{ width: "50%" }}>
        <CardContent>
          <Tabs variant="fullWidth" value={currTab} onChange={handleChangeTabs}>
            <Tab label="Login" />
            <Tab label="Cadastro" />
          </Tabs>
          {currTab === 0 ? (
            <Login onLogIn={handleLogin} />
          ) : (
            <Cadastro onCadastro={handleCadastro} />
          )}
        </CardContent>
      </Card>
    </main>
  );
};

const Login = ({ onLogIn }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(false);

  const logar = (e) => {

    logIn({email:email, password: password})
    .then((resp) => {
      try { 
        if (resp.data.email){
          console.log(resp.data)
          setCurrentUser(resp.data)
          onLogIn(); 
        }
      }catch{
        console.log("Ocorreu um porblema") 
      }
      setError(true); 
      return; 

    })

    .catch((e) => {
      setError(true);
      console.error(e.message, e);
    });
};

  return (
    <form className="formBox" >
      <Typography variant="h5">Login</Typography>
      <TextField
        required
        label="Email"
        variant="outlined"
        value={email}
        onChange={(event) => {
          setEmail(event.target.value);
          setError(false);
        }}
      />
      <TextField
        required
        type="password"
        label="Senha"
        variant="outlined"
        value={password}
        onChange={(event) => {
          setPassword(event.target.value);
          setError(false);
        }}
      />
      {error ? <Alert severity="error">Email ou senha inválidos!</Alert> : ""}
      <Button onClick={() => logar() } variant="contained">
        login
      </Button>
    </form>
  );
};

const Cadastro = ({ onCadastro }) => {
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [errors, setErrors] = useState({
    password: false,
    confirmPassword: false,
  });

  const cadastrar = (event) => {
    event.preventDefault();
    const isValid = !errors.password && !errors.confirmPassword;

    if (!isValid) return;

    createUser({ name:nome, email, password})
    .then((response) => {
      if (response) onCadastro();
    });
  };

  return (
    <form className="formBox" onSubmit={cadastrar}>
      <Typography variant="h5">Cadastro</Typography>
      <TextField
        required
        label="Nome"
        variant="outlined"
        value={nome}
        onChange={(event) => setNome(event.target.value)}
      />
      <TextField
        required
        label="Email"
        variant="outlined"
        value={email}
        onChange={(event) => setEmail(event.target.value)}
      />
      <Box sx={{ display: "flex", gap: 1 }}>
        <TextField
          fullWidth
          required
          error={errors.password}
          label="Senha"
          type="password"
          variant="outlined"
          value={password}
          helperText={
            errors.password ? "Senha deve ter mais de 5 caracteres" : ""
          }
          onChange={(event) => {
            setPassword(event.target.value);
            setErrors({
              ...errors,
              password:
                event.target.value.length > 0 && event.target.value.length < 5,
            });
          }}
        />
        <TextField
          fullWidth
          error={errors.confirmPassword}
          required
          label="Confirmar Senha"
          type="password"
          variant="outlined"
          value={confirmPassword}
          helperText={errors.confirmPassword ? "Senhas devem ser iguais" : ""}
          onChange={(event) => {
            setConfirmPassword(event.target.value);
            setErrors({
              ...errors,
              confirmPassword:
                event.target.value.length > 0 &&
                (errors.password || password !== event.target.value),
            });
          }}
        />
      </Box>
      <Button type="submit" variant="contained">
        cadastrar
      </Button>
    </form>
  );
};

export default LoginRegisterPage;