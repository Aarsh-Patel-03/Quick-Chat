import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import '../css/ViewProfileComponent.css';
import { FaArrowLeft, FaPen } from 'react-icons/fa';
export default function ViewProfile() {
  const [user, setUser] = useState({
    username: '',
    nickname: '',
    email: '',
    about: '',
    dateOfBirth: '',
    country: ''
  });

  const username = localStorage.getItem('username');

  useEffect(() => {
    // Fetch user data from the backend and populate the form
    axios.get(`http://localhost:8000/users/${username}`)
      .then(response => {
        setUser(response.data);
      })
      .catch(error => {
        console.error('Error fetching user data:', error);
      });
  }, [username]);

  return (
    <div className="view-profile-container">
      <h2>Profile</h2>
      <div className="profile-box">
        <div className="profile-button-group">
          <Link to="/home"  className='faLeft' >< FaArrowLeft /></Link>
          <Link to="/editprofile" className='faPen'><FaPen /> </Link>
        </div>
        <div className="view-profile-form">
          <div className="profile-form-group">
            <label>Username:</label>
            <div className='values'>{username}</div>
          </div>
          <div className="profile-form-group">
            <label>Nickname:</label>
            <div className='values'>{user.nickname}</div>
          </div>
          <div className="profile-form-group">
            <label>Email:</label>
            <div className='values'>{user.email}</div>
          </div>
          <div className="profile-form-group">
            <label>Date of Birth:</label>
            <div className='values'>{user.dateOfBirth}</div>
          </div>
          <div className="profile-form-group">
            <label>Country:</label>
            <div className='values'>{user.country}</div>
          </div>
          <div className="profile-form-group">
            <label>About:</label>
            <div className='values'>{user.about}</div>
          </div>
        </div>
      </div>
    </div>
  );
}
