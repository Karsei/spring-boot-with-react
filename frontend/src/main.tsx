// React
import React from 'react'
import ReactDOM from 'react-dom/client'
// Axios
import axios from 'axios';
// Components
import App from './App'
// Styles
import './index.css'

// Axios Settings
axios.defaults.headers.common['Pragma'] = 'no-cache'
axios.defaults.headers.common['Cache-Control'] = 'no-cache'
axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=utf-8'
axios.defaults.withCredentials = true
axios.interceptors.response.use(
    (response) => {
        return response
    },
    (error) => {
        return error
    }
)

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
)
