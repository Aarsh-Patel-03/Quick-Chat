import React, { useState } from 'react';
import '../css/RegisterComponent.css';
import axios from 'axios'

import {useNavigate  } from 'react-router-dom';

export default function RegisterComponent() {

  let navigate = useNavigate();

  const [showPassword, setShowPassword] = useState(false);
  const [showReEnterPassword, setShowReEnterPassword] = useState(false);
  // const [pswd, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const [user ,setUser ]  = useState({
    userName :"",
    email:"",
    password:""

  });
const {useName , email , password}  = user; 
const onInputChange = (e)=>{
    setUser({...user , [ e.target.name]: e.target.value  })
}

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };
  const toggleReEnterPasswordVisibility = () => {
    setShowReEnterPassword(!showReEnterPassword);
  };
  // const handlePasswordChange = (e) => {
  //   setPassword(e.target.value);
  // };
  const handleConfirmPasswordChange = (e) => {
    setConfirmPassword(e.target.value);
  };
  const onSubmit=async(e)=>{
    e.preventDefault();
    console.log(user);
    if (password !== confirmPassword) {
      alert('Passwords do not match');
      return;
    }
    console.log(user);
    await axios.post('http://localhost:8000/register', user)
    .then(response=>{
      console.log("user cretae " , response.data);
      alert("User cretaed successfully");
      navigate('/');
    })
    .catch(error=>{
      console.log("Error creating  user", error.response.data);
      alert(error.response.data);

    })
    // await axios.post("http://localhost:8000/abc", user);
   
    
  }

  // useEffect(() => {
  //   // Fetch user data from the backend
  //   const fetchUserData = async () => {
  //     try {
  //       const response = await axios.get(`http://localhost:8080/users/${localStorage.getItem('userName')}`);
  //       setUser(response.data);
  //     } catch (error) {
  //       console.error('Error fetching user data:', error);
  //     }
  //   };

  //   fetchUserData();
  // }, []);

  // const handleChange = (e) => {
  //   setUser({ ...user, [e.target.name]: e.target.value });
  // };
  
  return (
    <div className="main-container">
      <div className="register-container">
        <h2>Register</h2>
        <form  onSubmit={(e)=>onSubmit(e)} >
          <div className="form-group">
            <label htmlFor="name">Username</label>
            <div className="password-input">
            <input type="text" id="name" value={useName} name="userName" placeholder="Enter username" onChange={(e)=>onInputChange(e)} />
            </div>
            
          </div>
          <div className="form-group">
            <label htmlFor="email">Email</label>
            <div className="password-input">
            <input type="email" id="email" value={email} name="email" placeholder="Enter email" onChange={(e)=>onInputChange(e)} />
            </div>
            
          </div>
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <div className="password-input">
              <input
                type={showPassword ? 'text' : 'password'}
                id="password"
                value={password}
                name="password"
                placeholder="Enter password"
                onChange={(e)=>{onInputChange(e)}}
              
                // onChange={handlePasswordChange}
              />
              <button type="button" onClick={togglePasswordVisibility} className="show-password-btn">
                {showPassword ? 'Hide' : 'Show'}
              </button>
            </div>
          </div>
          <div className="form-group">
            <label htmlFor="confirmPassword">Confirm Password</label>
            <div className="password-input">
            <input
              type={showReEnterPassword ? 'text' : 'password'}
              id="confirmPassword"
              name="confirmPassword"
              placeholder="Re-enter password"
              value={confirmPassword}
              onChange={(e) => {
                handleConfirmPasswordChange(e);
                
            }}
            
            
            /><button type="button" onClick={toggleReEnterPasswordVisibility} className="show-password-btn">
            {showReEnterPassword ? 'Hide' : 'Show'}
          </button>
            </div>
          </div>
          <input type="submit" value="Register" className="register-btn" />
        </form>
        <p className="login-message">Already have an account? <a href="/">Login here</a></p>
      </div>
    </div>
  );
}
