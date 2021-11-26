import './App.css';
import React, { useState } from 'react';
import Login from './components/login/Login'
import {Routes, Route} from 'react-router-dom';
import Dashboard from './components/dashboard/Dashboard';
import Preferences from './components/preferences/Preferences';
/*import Dashboard from './Dashboard'
import Preferences from './Preferences';
import Home from './Home';*/

function App() {
  const [token, setToken] = useState();

  if(!token) {
    return <Login setToken={setToken} />
  }

  return (
    <div className="wrapper">
      <h1>Application</h1>
      <Routes>
          <Route path="/dashboard" element={<Dashboard />} />
          
          <Route path="/preferences" element={<Preferences />}/>
      </Routes>
    </div>
  );
}

export default App;
