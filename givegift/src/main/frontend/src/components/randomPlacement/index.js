import React, {useEffect, useState} from "react";
import useLocalState from "../../utils/localStorage";
import "./index.css";
import MyHeader from "../header";

const RandomPlacement = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [myBalance, setMyBalance] = useLocalState(0, "myBalance");
    const [upd, setUpd] = useState(0);
    const [placement, setPlacement] = useState(null);

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
        [upd]
    );

    function getRandomPlacement() {
        fetch("/api/randomPlacement", {
            headers: {
                "content-type": "application/json",
                Authorization: `Bearer ${jwt}`,
            },
            method: "get"
        }).then(response => {
            if (response.status === 200) {
                return response.json();
            }
            else {
                alert("Что то пошло не так");
            }
        }).then(placementData => {
            console.log(placementData);
            window.location.href = `/placements/${placementData.id}`;
        });
    }

    getRandomPlacement();

    return (
        <>
        <MyHeader></MyHeader>
        <div className="randomPlacement">
            <div className="block">
                Загрузка
            </div>
        </div>
        </>
    );
};

export default RandomPlacement;