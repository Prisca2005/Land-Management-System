import axios from 'axios';

const api = axios.create({
  baseURL: '/api'
});

// Add auth token to requests
api.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export const login = (credentials) => api.post('/auth/login', credentials);
export const register = (userData) => api.post('/auth/register', userData);
export const getLandParcels = () => api.get('/parcels');
export const getLandParcel = (id) => api.get(`/parcels/${id}`);
export const createLandParcel = (data) => api.post('/parcels', data);
export const updateLandParcel = (id, data) => api.put(`/parcels/${id}`, data);
export const deleteLandParcel = (id) => api.delete(`/parcels/${id}`);

export default api;