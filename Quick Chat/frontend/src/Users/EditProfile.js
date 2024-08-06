import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { FaArrowLeft} from 'react-icons/fa';
import '../css/EditProfileComponent.css';

export default function EditProfile() {
  const username = localStorage.getItem('username');

  const [user, setUser] = useState({
    username: username,
    nickname: '', // Assuming nickname is unique
    email: '',
    about: '', // Assuming 'about' is the description field
    dateOfBirth: '', // Assuming 'dateOfBirth' is the user's date of birth
    country: '' // Assuming 'country' is the user's country
  });

  useEffect(() => {
    // Fetch user data from the backend and populate the form
    axios.get(`http://localhost:8000/users/${username}`)
      .then(response => {
        setUser(response.data);
      })
      .catch(error => {
        console.error('Error fetching user data:', error);
      });
  }, [username]); // Make sure to include user.nickname in the dependency array

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSave = () => {
    // Send a POST request to save user details
    axios.post(`http://localhost:8000/users/${username}`, user)
      .then(response => {
        alert("User details saved successfully");
        console.log('User details saved successfully:', response.data);
        // Handle any success actions here
      })
      .catch(error => {
        console.error('Error saving user details:', error);
        // Handle any error actions here
      });
  };

  return (
    <div className="edit-profile-container">
      <h2>Edit Profile</h2>
      <div className="profile-box">
        <div className="profile-image">
          {/* Display the user's image */}
          {user.image && <img src={user.image} alt="Profile" />}
          <div className="button-group">
            <Link to="/viewprofile" className='faLeft' >< FaArrowLeft /></Link>
          </div>
        </div>
        <form className="edit-profile-form">
        <div className="edit-profile-form-group">
            <label htmlFor="userName">Username: </label>
            <input type="text" id="userName" name="userName" value={user.userName} onChange={handleChange} placeholder={username} displayed/>
          </div>
          <div className="edit-profile-form-group">
            <label htmlFor="nickname">Nickname:</label>
            <input type="text" id="nickname" name="nickname" value={user.nickname} onChange={handleChange} placeholder="Nickname" />
          </div>
          <div className="edit-profile-form-group">
            <label htmlFor="email">Email:</label>
            <input type="email" id="email" name="email" value={user.email} onChange={handleChange} placeholder="Email" />
          </div>
          <div className="edit-profile-form-group">
            <label htmlFor="dateOfBirth">Date of Birth:</label>
            <input type="date" id="dateOfBirth" name="dateOfBirth" value={user.dateOfBirth} onChange={handleChange} />
          </div>
          <div className="edit-profile-form-group">
            <label htmlFor="country">Country:</label>
            <input type="text" id="country" name="country" value={user.country} onChange={handleChange} placeholder="Country" />
          </div>
          <div className="edit-profile-form-group">
            <label htmlFor="about">About:</label>
            <textarea id="about" name="about" value={user.about} onChange={handleChange} placeholder="About" />
          </div>
        </form>
        <div className="button-center">
          <button onClick={handleSave}>Save</button>
        </div>
      </div>
    </div>
  );
}
