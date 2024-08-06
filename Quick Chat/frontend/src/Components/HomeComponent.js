import React, { useState } from 'react';
import { NavbarComponent } from './NavbarComponent';
import ChatComponent from './ChatComponent';
import ConnectionComponent from './ConnectionComponent';

export default function HomeComponent({username}) {
    const [showConnection, setShowConnection] = useState(false); // Initial state to show ChatComponent
    

    const handleAddPeopleClick = () => {
        // Reload ChatComponent by toggling the state
        setShowConnection(!showConnection);
    };
    const handleLogoClick=()=>{
           setShowConnection(false);
    
    }


    return (
        <div className="home">
            <NavbarComponent handleAddPeopleClick={handleAddPeopleClick } handleLogoClick={handleLogoClick} />
            {  showConnection ?  <ConnectionComponent/> :  <ChatComponent /> }
        
        </div>
    );
}
