import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

const DetailsEtudiant = () => {

  const [notes, setNotes] = useState([]);
  const [etudiant, setEtudiant] = useState('');
  const [nomDuCours, setNomDuCours] = useState('');
  const [valeurDeNote, setValeurDeNote] = useState('');

  const { id } = useParams();


  useEffect(() => {
    fetch(`http://api.aseds.inpt.com/notes/${id}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error('Failed to fetch notes');
      }
      return response.json();
    })
    .then((data) => setNotes(data))
    .catch((error) => {
      console.error('Error fetching notes:', error)});
  }, [id]);

  useEffect(() => {
    fetch(`http://api.aseds.inpt.com/etudiants/${id}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error('Failed to fetch notes');
      }
      return response.json();
    })
    .then((data) => setEtudiant(data))
    .catch((error) => {
      console.error('Error fetching notes:', error)});
  }, [id]);

  // Handle form submission to add a new note
  const handleAddNote = (e) => {
    e.preventDefault();
    // Send POST request to add the note
    fetch(`http://api.aseds.inpt.com/notes/${id}?etudiantId+${id}&nomDuCours=${nomDuCours}&valeurDeNote=${valeurDeNote}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then((response) => response.json())
      .then((data) => {
        // Update the notes state to include the new note
        setNotes((prevNotes) => [...prevNotes, data]);
        // Reset form fields
        setNomDuCours('');
        setValeurDeNote('');
      })
      .catch((error) => {
        console.error('Error adding note:', error);
      });
  };


  if (!etudiant) {
    return <div>Loading...</div>;
  }


  return (
    <div className="max-w-[1200px] mx-4 lg:mx-8 xl:mx-auto mt-8">
       <div className="md:flex md:justify-between mb-6">
      <h1 className="font-bold text-3xl">Liste des notes de {etudiant.nom}</h1>
      {/* Form to add a new note */}
      <form onSubmit={handleAddNote} className="mt-4 md:mt-0 flex gap-6 items-end">
        <div className="mb-2">
          <label htmlFor="nomDuCours" className="block font-semibold">
            Nom du cours
          </label>
          <input
            type="text"
            id="nomDuCours"
            value={nomDuCours}
            onChange={(e) => setNomDuCours(e.target.value)}
            required
            className="border border-gray-300 p-2 w-full"
          />
        </div>
        <div className="mb-2">
          <label htmlFor="valeurDeNote" className="block font-semibold">
            Note
          </label>
          <input
            type="number"
            id="valeurDeNote"
            value={valeurDeNote}
            onChange={(e) => setValeurDeNote(e.target.value)}
            required
            step="0.25"
            className="border border-gray-300 p-2 w-full"
          />
        </div>
        <button
          type="submit"
          className="bg-blue-600 text-white p-2 rounded-md mb-3"
        >
          Ajouter la note
        </button>
      </form>
      </div>
      <table className="min-w-full border-collapse border border-black">
        <thead>
          <tr className="bg-gray-300">
            
            <th className="border border-black px-4 py-2 text-left">Nom du cours</th>
            <th className="border border-black px-4 py-2 text-left">Note</th>
          </tr>
        </thead>
        <tbody>
          {notes.map((note) => (
            <tr key={note.id} className={note.valeurDeNote >= 10 ? 'bg-green-200' : 'bg-red-200'} // Applique une classe conditionnelle
            >
              <td className="border border-black px-4 py-2">{note.nomDuCours}</td>
              <td className="border border-black px-4 py-2">{note.valeurDeNote}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default DetailsEtudiant;
