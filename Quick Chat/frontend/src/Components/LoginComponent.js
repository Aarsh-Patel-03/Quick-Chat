import React, { useState } from 'react';
import '../css/LoginComponent.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function LoginComponent() {
  const [showPassword, setShowPassword] = useState(false);
  const [credentials, setCredentials] = useState({
    userName: "",
    password: ""
  });
  const { userName, password } = credentials;

  let navigate = useNavigate();

  const onInputChange = (e) => {
    setCredentials({ ...credentials, [e.target.name]: e.target.value })
  }

  const onSubmit = async (e) => {
    e.preventDefault();
    console.log(credentials);
    await axios.post('http://localhost:8000/login', credentials)
      .then(response => {
        console.log("user create ", response);
        alert("User login successfully");
        localStorage.setItem('username', credentials.userName);
        navigate('/home');
      })
      .catch(error => {
        console.log("Error creating  user", error.response);
        alert("error");

      })
  }

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };


  return (
    <div className="main-container">
      <div className="login-container">
        <h2>Login</h2>
        <form onSubmit={(e) => onSubmit(e)} >
          <div className="form-group">
            <label htmlFor="username">Username</label>
            <div className='password-input'>
              <input type="text" id="username" value={userName} name="userName" onChange={(e) => { onInputChange(e) }} placeholder="Enter username" />
            </div>

          </div>
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <div className="password-input">
              <input
                type={showPassword ? 'text' : 'password'}
                id="password"
                name="password"
                placeholder="Enter password"
                value={password}
                onChange={(e) => { onInputChange(e) }}
              />
              <button type="button" onClick={togglePasswordVisibility} className="show-password-btn">
                {showPassword ? 'Hide' : 'Show'}
              </button>
            </div>
          </div>
          <input type="submit" value="Login" className="login-btn" />
        </form>

        <p className="register-message">Don't have an account? <a href="/register">Register</a></p>
      </div>
    </div>
  );
}
