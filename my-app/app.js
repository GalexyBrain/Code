import React, { useEffect, useState } from 'react';
import axios from 'axios';

function App() {
    const [message, setMessage] = useState('');
    const [postResponse, setPostResponse] = useState('');

    useEffect(() => {
        // GET request
        axios.get('http://127.0.0.1:5000/api/data')
            .then(response => {
                setMessage(response.data.message);
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    }, []);

    const postData = () => {
        // POST request
        axios.post('http://127.0.0.1:5000/api/data', { data: 'Hello from React!' })
            .then(response => {
                setPostResponse(response.data.received.data);
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    };

    return (
        <div className="App">
            <h1>Message from Flask: {message}</h1>
            <button onClick={postData}>Send Data to Flask</button>
            <h2>Response from Flask: {postResponse}</h2>
        </div>
    );
}

export default App;
