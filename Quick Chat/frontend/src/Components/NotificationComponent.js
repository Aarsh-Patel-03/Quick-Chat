import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { FaArrowLeft, FaBackward, FaEdit, FaPen } from 'react-icons/fa';
import '../css/NotificationComponent.css';

export default function NotificationComponent() {
    const [friendRequests, setFriendRequests] = useState([]);
    const username = localStorage.getItem('username'); // Fetch current user's username

    useEffect(() => {
        // Fetch friend requests data for the current user from the database
        axios.get(`http://localhost:8000/friend-requests/${username}`)
            .then(response => {
                setFriendRequests(response.data); // Update state with fetched friend requests data
            })
            .catch(error => {
                console.error('Error fetching friend requests data:', error);
            });
    }, [username, friendRequests]); // Include username in the dependency array to fetch data when it changes

    const handleAcceptRequest = (id) => {
        // Handle accepting the friend request with id
        axios.post(`http://localhost:8000/friend-requests/${id}/accept`, { accepted: true })
            .then(response => {
                // Remove the accepted friend request from the UI
                setFriendRequests(friendRequests.filter(request => request.id !== id));
                console.log('Friend request accepted successfully:', response.data);
            })
            .catch(error => {
                console.error('Error accepting friend request:', error);
            });
    };    

    const handleRemoveRequest = (id) => {
        // Handle removing the friend request with id
        axios.delete(`http://localhost:8000/friend-requests/${id}`)
            .then(response => {
                // Remove the friend request from the UI
                setFriendRequests(friendRequests.filter(request => request.id !== id));
                console.log('Friend request removed successfully:', response.data);
            })
            .catch(error => {
                console.error('Error removing friend request:', error);
            });
    };

    return (
        <div className="notification-container">
            <div className='notification-main'>
                <h2>Friend Requests</h2>
                <div className="back-button">
                    <Link to="/home" className="back-link">< FaArrowLeft size={25}/></Link>
                </div>
            </div>
            <ul className="friend-requests-list">
                {friendRequests.map(request => (
                    <li key={request.id}>
                        <span>{request.senderUsername}</span> sent you a friend request.
                        <div className='buttons'>
                            <button className="accept-button" onClick={() => handleAcceptRequest(request.id)}>
                                Accept
                            </button>
                            <button className="remove-button" onClick={() => handleRemoveRequest(request.id)}>
                                Remove
                            </button>
                        </div>
                    </li>
                ))}
            </ul>
            {/* {pendingNotifications > 0 && <div className="notification-count">{pendingNotifications}</div>} */}
        </div>
    );
};
