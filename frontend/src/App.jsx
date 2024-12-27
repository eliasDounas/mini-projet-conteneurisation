import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import './App.css'
import DetailsEtudiant from './components/DetailsEtudiant'
import TableauEtudiants from './components/TableauEtudiants'

function App() {

   return (
      <Router>
        <div className="App">
          <Routes>
            <Route path="/" element={<TableauEtudiants />} />
            <Route path="/etudiants/:id" element={<DetailsEtudiant />} />
          </Routes>
        </div>
      </Router>
    );
  }
  


export default App
