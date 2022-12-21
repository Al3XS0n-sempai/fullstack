import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import './index.css'
import useLocalState from "../../utils/localStorage";

const MyHeader = () => {
    const [myBalance, setMyBalance] = useLocalState(0, "myBalance");
    const [jwt, setJwt] = useLocalState("", "jwt");
    useEffect( 
        () => {
            fetch("/api/balance", {
                headers: {
                    "content-type": "application/json",
                    Authorization: `Bearer ${jwt}`
                },
                method: "get",
    
            }).then(response => {
                if (response.status === 200){
                    return response.json();
                }
            }).then(balanceCred => {
                setMyBalance(balanceCred.balance);
            })
        },
        []
    );

    return (
        <div className="header">
            <div className="left">
                <h1>GiveGift</h1>
                <Link to="/dashboard">Dashboard</Link>
                <Link to="/randomPlacement">Удача</Link>
            </div>
            <div className="mid">
                <Link to="/balance">Баланс : {myBalance}$</Link>
            </div>
            <div className="right">
                <button onClick={() => {
                    setJwt(""); 
                    window.location.href = "/login";
            }}>ВЫЙТИ</button>
            </div>
        </div>
    );
}

export default MyHeader;
