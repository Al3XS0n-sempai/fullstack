import React, {useState} from "react";
import useLocalState from "../../utils/localStorage";
import { Link } from "react-router-dom";
import "./index.css";

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [jwt, setJwt] = useLocalState("", "jwt");

    function sendLoginRequest() {
        console.log("Sending request");

        const request_body = {
            "username": username,
            "password": password
        }
        fetch("api/auth/login", {
            headers: {
                "Content-type": "application/json"
            },
            method: "post",
            body: JSON.stringify(request_body)
        }).then((response) => {
            if (response.status === 200) {
                return Promise.all([response.json(), response.headers]);
            } else {
                return Promise.reject("Неверное имя пользователя или пароль");
            }
        })
            .then(([body, headers]) => {
                setJwt(headers.get("authorization"));
                window.location.href = "/dashboard";
            })
            .catch((message) => { alert(message); });
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
                <button id="submit" type="button" onClick={() => sendLoginRequest() }>Войти</button>
            </div>
            <div className="gridItem">
                Нет акаунта?
                <Link to="/registration">Создать</Link>
            </div>
        </div>
    );
};

export default Login;