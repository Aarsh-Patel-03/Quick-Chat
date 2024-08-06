import React from 'react'
import HomeComponent from '../Components/HomeComponent'
// import axios from 'axios'
export default function Home() {
  const username = localStorage.getItem('username');
    // const [users,setUsers] = useState([])
    // useEffect(()=>{
    //     loadUsers();
    // },[])

    // const loadUsers = async()=>{
    //     const result = await axios.get("http://localhost:5000/users")
    //     console.log(result);
    // }
  return (
    <div>
      {/* <h1>Home</h1> */}
      <HomeComponent username={username}/>
    </div>
  )
}
