import React, {useEffect, useState} from "react";
import useLocalState from "../../utils/localStorage";
import { Link } from "react-router-dom";
import "./index.css";
import MyHeader from "../header";

const Dashboard = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [myBalance, setMyBalance] = useLocalState(0, "myBalance");
    const [placements, setPlacements] = useState(null);
    const [upd, setUpd] = useState(0);
    const [newPlacementName, setNewPlacementName] = useState("");
    const [newPlacementDescription, setNewPlacementDescription] = useState("");
    const [newPlacementTargetSum, setNewPlacementTargetSum] = useState(0);

    useEffect(() => {
        fetch("/api/placements", {
            headers: {
                "content-type": "application/json",
                Authorization: `Bearer ${jwt}`
            },
            method: "get",

        }).then(response => {
            if (response.status === 200){
                return response.json();
            }
        }).then(placementData => {
            setPlacements(placementData);
        })
    }, [upd]);

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

    function createPlacement() {
        if (placements && placements.length == 3) {
            alert('Нельзя иметь больше 3 размещений')
            return;
        }
        if (newPlacementName === "") {
            alert("Название не должно быть пустым!");
            return;
        }
        if (newPlacementTargetSum === 0 || newPlacementTargetSum < 0 || newPlacementTargetSum > 100000) {
            alert("Не допустимое число");
            return;
        }
        fetch("/api/placements", {
            headers: {
                "content-type": "application/json",
                Authorization: `Bearer ${jwt}`,
            },
            method: "post",
            body: JSON.stringify({
                "name": newPlacementName,
                "description": newPlacementDescription,
                "targetSum": newPlacementTargetSum
            })
        }).then(response => {
            if (response.status === 200) {
                return response.json();
            }
        }).then(placement => {
            setUpd(1 - upd);
        }).catch((error) => alert("Не вышло"));
    }

    function deletePlacement(id) {
        fetch(`/api/placements/${id}`, {
            headers: {
                "content-type": "application/json",
                Authorization: `Bearer ${jwt}`,
            },
            method: "delete"
        }).then(response => {
            if (response.status === 200) {
                setUpd(1 - upd);
            }
        })
    }
    return (
        <>
        <MyHeader></MyHeader>
        <div className="dashboard">
            <div className="myPlacements">
                <h1>Ваши размещения</h1>
                {
                placements 
                ? placements.map((placement) => 
                    { 
                        return (<div className="placement">
                            <Link to={`/placements/${placement.id}`}>
                                Размещение : {placement.name}
                            </Link>
                            <button onClick={() => deletePlacement(placement.id)}>Удалить</button>
                        </div>); 
                    }) 
                : <></>}
            </div>
            <div className="newPlacement">
                <label htmlFor="placementName">Название</label>
                <input type="text" id="placementName" value={newPlacementName}
                       onChange={(event) => setNewPlacementName(event.target.value)}/>
                <label htmlFor="placementName">Целевая сумма</label>
                <input type="number" id="placementTargetSum" min="0" max="100000" value={newPlacementTargetSum}
                       onChange={(event) => {
                            if (event.target.value <= 100000) {
                                setNewPlacementTargetSum(event.target.value);
                            }
                       }}/>
                <label htmlFor="placementName">Описание</label>
                <textarea id="placementDescription" value={newPlacementDescription}
                       onChange={(event) => {
                        if (event.target.value.length <= 100) {
                            setNewPlacementDescription(event.target.value);
                        }
                        }}/>
                <div className="buttonWrapper">
                    <button onClick={() => createPlacement()}>Создать новое размещение</button>
                </div>
            </div>
        </div>
        </>
    );
};

export default Dashboard;