import {createBrowserRouter} from "react-router-dom";
import Login from "../Pages/Login";
import Registration from "../Pages/Registration";
import Home from "../Pages/Home";
import ConnectionComponent from "../Components/ConnectionComponent";
import ViewProfile from "../Users/ViewProfile";
import EditProfile from "../Users/EditProfile";
import NotificationComponent from "../Components/NotificationComponent";
import RequestsComponent from "../Components/RequestsComponent";

export const router = createBrowserRouter([
    {
      path: "/",
      element:<Login/>,
    },
    {
        path: "/register",
        element:<Registration/>,
      },
      {
        path: "/home",
        element:<Home/>,
      },
      {
        path: "/connection",
        element:<ConnectionComponent/>,
      },
      {  
        path: "/notification",
        element:<NotificationComponent />

      },
      {  
        path: "/requests",
        element:<RequestsComponent />

      },
      {
        path: "/viewprofile",
        element: <ViewProfile />
      },
      {
        path: "/editprofile",
        element: <EditProfile />
      }
  ]);
   