import React, {useEffect, useState} from "react";
import useLocalState from "../../utils/localStorage";
import "./index.css";
import MyHeader from "../header";

const Balance = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [myBalance, setMyBalance] = useLocalState(0, "myBalance");
    const [upd, setUpd] = useState(0);
    const [funds, setFunds] = useState(0);

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

    function makeRefill() {
        fetch("/api/balance", {
            headers: {
                "content-type": "application/json",
                Authorization: `Bearer ${jwt}`,
            },
            method: "post",
            body: JSON.stringify({
                "balance": funds
            })
        }).then(response => {
            if (response.status === 200) {
                setUpd(1 - upd);
                setFunds(0);
            }
            else {
                alert("Что то пошло не так");
            }
        });
    }

    return (
        <>
        <MyHeader></MyHeader>
        <div className="refill">
            <input type="number" id="funds" min="0" max="10000" value={funds} onChange={(event) => setFunds(event.target.value)}/>
            <button onClick={() => makeRefill() }>Пополнить счет</button>
        </div>
        </>
    );
};

export default Balance;