import React, {useEffect, useState} from "react";
import useLocalState from "../../utils/localStorage";

const Registration = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [repeatPassword, setRepeatPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    useEffect(() => {
        if (repeatPassword !== password) {
            setErrorMessage("Пароли не совпадают!");
        } else {
            setErrorMessage("");
        }
    }, [password, repeatPassword]);

    // console.log(password);
    function sendRegistrationRequest() {
        if (username === "") {
            alert("Имя пользователя не может быть пустым");
            return;
        }
        if (password !== repeatPassword) {
            alert("Пароли не совпадают");
            return;
        }
        if (password === "") {
            alert("Пароль не может быть пустым");
            return;
        }
        console.log("Sending request");

        const request_body = {
            "username": username,
            "password": password
        }
        fetch("api/auth/registration", {
            headers: {
                "Content-type": "application/json"
            },
            method: "post",
            body: JSON.stringify(request_body)
        }).then((response) => {
            if (response.status !== 200) {
                return Promise.reject("Имя пользователя занято");
            } else {
                window.location.href = "/dashboard";
            }
        }).catch((message) => { alert(message); });
    }
    return (
        <div className="grid">
            <div className="gridItem">
                <label htmlFor='username'>Логин</label>
                <input type="text" id="username" value={username}
                       onChange={(event) => setUsername(event.target.value)}/>
            </div>
            <div className="gridItem">
                <label htmlFor='password'>Пароль</label>
                <input type="password" id="password" value={password}
                       onChange={(event) => setPassword(event.target.value)}/>
            </div>
            <div className="gridItem">
                <label htmlFor='password'>Повторите пароль</label>
                <input type="password" id="repeatPassword" value={repeatPassword}
                        onChange={(event) => {setRepeatPassword(event.target.value);}}/>
            </div>
            <div className="errorMessage">
                {errorMessage}
            </div>
            <div className="gridItem">
                <button id="submit" type="button" onClick={() => sendRegistrationRequest() }>Зарегестрироваться</button>
            </div>
        </div>
    );
};

export default Registration;