import {Route, Routes} from "react-router";
import Dashboard from "./components/dashboard";
import Homepage from "./components/homepage";
import Login from "./components/login";
import PrivateRoute from "./utils/PrivateRoute";
import PlacementView from "./components/placementView";
import Registration from "./components/registration";
import Balance from "./components/balance";
import RandomPlacement from "./components/randomPlacement";

function App() {
    return (
        <Routes>
            <Route path="/dashboard" element={<PrivateRoute><Dashboard/></PrivateRoute>}></Route>
            <Route path="/placements/:id" element={<PrivateRoute><PlacementView/></PrivateRoute>}></Route>
            <Route path="/login" element={<Login/>}></Route>
            <Route path="/registration" element={<Registration/>}></Route>
            <Route path="/balance" element={<PrivateRoute><Balance/></PrivateRoute>}></Route>
            <Route path="/randomPlacement" element={<PrivateRoute><RandomPlacement/></PrivateRoute>}></Route>
            <Route path="/" element={<PrivateRoute><Dashboard/></PrivateRoute>}></Route>
        </Routes>
    );
}

export default App;