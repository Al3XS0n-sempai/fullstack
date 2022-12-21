import React, { useEffect, useState } from "react";
import useLocalState from "../../utils/localStorage";
import "./index.css";
import MyHeader from "../header";

const placementView = () => {
    const id = window.location.href.split("/placements/")[1];
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [placement, setPlacement] = useState(null);
    const [updCollected, setUpdCollected] = useState(0);
    const [donation, setDonation] = useState(0);

    useEffect(() => {
        fetch(`/api/placements/${id}`, {
            headers: {
                "content-type": "application/json",
                Authorization: `Bearer ${jwt}`
            },
            method: "get",
        }).then((response) => {
            if (response.status === 200) {
                return response.json();
            }
        })
        .then((placementData) => {
            setPlacement(placementData);
        })
    }, [updCollected]);

    function donate() {
        console.log(`Making donation: ${donation} ${id}`)
        fetch(`/api/donations`, {
            headers: {
                "content-type": "application/json",
                Authorization: `Bearer ${jwt}`
            },
            method: "post",
            body: JSON.stringify({
                "amount": donation,
                "placementId": Number(id)
            })
        }).then((response) => {
            if (response.status === 200) {
                console.log("WTF");
                setUpdCollected(1 - updCollected);
            } else {
                Promise.reject("Неверное имя пользователя или пароль");
            }
        }).catch((message) => { alert(message); });
    }
    return (
        <div className="placementView">
            <MyHeader></MyHeader>
            <h1>
                Размещение номер {id}
            </h1>
            {placement ? 
            (
                <>
                    <h2>Автор: {placement.author.username}</h2>
                    <h2>Описание: {placement.description} </h2>
                    <h2>Собранно: {placement.collected} из {placement.targetSum}</h2>
                    <div class="donation">
                        <label htmlFor='donation_amount'>Сумма подарка</label>
                        <input type="number" id='donation_amount' min="0" max="10000" value={donation} 
                            onChange={(event) => {setDonation(event.target.value)}}></input>
                        <button onClick={donate}>Подарить</button>
                    </div>
                </>
            ) : (<></>)}
        </div>
    );
};

export default placementView;