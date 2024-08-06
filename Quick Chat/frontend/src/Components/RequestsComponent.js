import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { FaArrowLeft, FaBackward, FaEdit, FaPen } from 'react-icons/fa';
import '../css/NotificationComponent.css';

export default function RequestsComponent() {
  const [pendingRequests, setPendingRequests] = useState([]);
    const username = localStorage.getItem('username'); // Fetch current user's username

    useEffect(() => {
        // Fetch friend requests data for the current user from the database
        axios.get(`http://localhost:8000/friend-requests/sender/${username}`)
            .then(response => {
                setPendingRequests(response.data); // Update state with fetched friend requests data
                console.log(response.data);
            })
            .catch(error => {
                console.error('Error fetching friend requests data:', error);
            });
    }, [username,pendingRequests]); // Include username in the dependency array to fetch data when it changes

    
    const handleRemoveRequest = (id) => {
        // Handle removing the friend request with id
        axios.delete(`http://localhost:8000/friend-requests/${id}`)
            .then(response => {
                // Remove the friend request from the UI
                setPendingRequests(pendingRequests.filter(request => request.id !== id));
                console.log('Friend request removed successfully:', response.data);
            })
            .catch(error => {
                console.error('Error removing friend request:', error);
            });
    };

    return (
        <div className="notification-container">
            <div className='request-main'>
            <h2>Pending Requests</h2>
            <div className="back-button">
                <Link to="/home" className="back-link">< FaArrowLeft size={25}/></Link>
            </div>
            </div>
            <ul className="friend-requests-list">
                {pendingRequests.map(request => (
                    <li key={request.id}>
                        <span>{request.receiverUsername}</span> 
                        <div className='buttons'>
                            <button className="remove-button" onClick={() => handleRemoveRequest(request.id)}>
                                Remove
                            </button>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
}
