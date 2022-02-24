import axios from "axios";

const apiURL = "http://localhost:8080/"

export var currentUser = {
  user: {
    id: -1,
    email: "",
    name: "",
  },
  isLoggedIn: false
}

const EMPTY_USER = {
  id: -1,
  email: "",
  name: "",
};

var defaultAxios = axios.create({
  baseURL: apiURL
});


export const logIn = async (user) => {
  const url = apiURL + "login";
  var response = await axios.post(url, user);
  return response;
};

export const getCurrentUser = () => {
  return currentUser;
};

export const setCurrentUser = (userNew) => {
  currentUser = {user: {...userNew}, isLoggedIn: true};
};

export const logout = async () => {
  currentUser = {user: {...EMPTY_USER}, isLoggedIn: false};
};

export const createUser = async (user) => {
  const url = apiURL + "register";
  var response = await axios.post(url, user);
  return response;
};

export const updateUser = async (id, newUser) => {
  const url = `update/${id}/`;
  await defaultAxios.put(url, newUser);
};

export const deleteUser = async (id) => {
  const url = `delete/${id}`;
  await defaultAxios.delete(url);
};
