import React from "react";
import useLocalState from "../../utils/localStorage";
import {Navigate} from "react-router";

const PrivateRoute = ({ children }) => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    return jwt ? children : <Navigate to="/login"></Navigate>;
}

export default PrivateRoute;