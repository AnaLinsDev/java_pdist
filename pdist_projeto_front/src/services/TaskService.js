import axios from "axios";
import { getCurrentUser } from "./UserService";

const apiURL = "http://localhost:8080/user/"



var defaultAxios = axios.create({
  baseURL: apiURL
});


export const findAllTasks = async () => {
  const currentUserAux = getCurrentUser();
  const currentUserId = currentUserAux.user.id;

  const url = apiURL + `${currentUserId}/tasks`;
  const response = await defaultAxios.get(url);
  return response.data;
};

export const createTask = async (event) => {
  const currentUserAux = getCurrentUser();
  const currentUserId = currentUserAux.user.id;

  delete event["id"]
  const url =  `${currentUserId}/create/task/`;
  const response = await defaultAxios.post(url, event);
  return response;
};

export const updateTask = async (id, newEvent) => {
  const currentUserAux = getCurrentUser();
  const currentUserId = currentUserAux.user.id;

  const url = `${currentUserId}/task/update/${id}/`;
  await defaultAxios.put(url, newEvent);
};

export const deleteTask = async (id) => {
  const currentUserAux = getCurrentUser();
  const currentUserId = currentUserAux.user.id;

  const url = `${currentUserId}/delete/task/${id}`;
  await defaultAxios.delete(url);
};