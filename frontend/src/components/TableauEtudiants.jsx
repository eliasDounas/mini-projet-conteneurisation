import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const TableauEtudiants = () => {
  const [etudiants, setEtudiants] = useState([]);
  const [newStudentName, setNewStudentName] = useState('');

  const navigate = useNavigate();
  
  useEffect(() => {
    fetch('http://api.aseds.inpt.com/etudiants')
      .then(response => response.json())
      .then(data => setEtudiants(data))
  }, [etudiants]);

  const handleAddEtudiant = (e) => {
    e.preventDefault();


    fetch(`http://api.aseds.inpt.com/etudiants?nom=${newStudentName}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then(response => response.json())
      .then(data => {
        setEtudiants((prevEtudiants) => [...prevEtudiants, data]); // Add the new student to the list
        setNewStudentName(''); // Clear the input field after adding
      })
      .catch((error) => {
        console.error('Error adding student:', error);
      });
  };


  const handleEtudiantClick = (id) => {
    navigate(`/etudiants/${id}`);
  };

  if (!etudiants) {
    return <div>Loading...</div>; // Or display a loading state
  }
  return (
    <div className="max-w-[1200px] mx-4 lg:mx-8 xl:mx-auto mt-8">
      <div className="flex justify-between mb-6">
        <h1 className="font-bold text-3xl inline ">Liste des Etudiants</h1>

        {/* Form to add a new Etudiant */}
        <form onSubmit={handleAddEtudiant} className="inline-block">
          <input
          type="text"
          placeholder="Nom de l'étudiant"
          value={newStudentName}
          onChange={(e) => setNewStudentName(e.target.value)}
          className="border p-2 mr-2"
        />
        <button type="submit" className="bg-blue-500 text-white p-2">Ajouter étudiant</button>
      </form>
    </div>
      <table className="min-w-full border-collapse border border-black">
        <thead>
          <tr className="bg-gray-300">
            <th className="border border-black px-4 py-2 text-left">Id</th>
            <th className="border border-black px-4 py-2 text-left">Nom</th>
            <th className="border border-black px-4 py-2 text-left">Date de création</th>
            <th className="border border-black px-4 py-2 text-left">Moyenne</th>
          </tr>
        </thead>
        <tbody>
          {etudiants.map((etudiant) => (
            <tr key={etudiant.id} className={etudiant.moyenne >= 10 ? 'bg-green-200' : 'bg-red-200'}>
              <td className="border border-black px-4 py-2">{etudiant.id}</td>
              <td className="border border-black px-4 py-2" onClick={() => handleEtudiantClick(etudiant.id)}>{etudiant.nom}</td>
              <td className="border border-black px-4 py-2">{etudiant.dateDeCreation}</td>
              <td className="border border-black px-4 py-2">{etudiant.moyenne}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default TableauEtudiants;
