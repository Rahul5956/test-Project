import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const NGORegistration = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [description, setDescription] = useState('');
  const [contactInfo, setContactInfo] = useState('');
  const [address, setAddress] = useState('');
  const [beneficiary, setBeneficiary] = useState('');
  const [sectionLicence, setSectionLicence] = useState('');
  const [registrationNumber, setRegistrationNumber] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [isRegistered, setIsRegistered] = useState(false);
  const navigate = useNavigate();

  const handleRegistration = async (event) => {
    event.preventDefault();
    setLoading(true);
    setError('');

    if (password !== confirmPassword) {
      setError('Passwords do not match');
      setLoading(false);
      return;
    }

    try {
      const res = await axios.post('http://localhost:8080/Ngos/add', {
        name,
        email,
        description,
        contactInfo,
        address,
        beneficiary,
        sectionLicence,
        registrationNumber,
        password,
      });

      setIsRegistered(true);

      // Redirect to landing page after a delay
      setTimeout(() => {
        navigate('/'); // Redirect to the LandingPage after successful registration
      }, 3000); // 3 seconds delay before redirection
    } catch (error) {
      setError('Registration failed. Please try again later.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      <form onSubmit={handleRegistration} className="bg-white p-6 rounded shadow-md w-full max-w-md">
        <h2 className="text-2xl font-bold mb-4">NGO Registration</h2>
        {error && <p className="text-red-500 mb-4">{error}</p>}
        <input
          type="text"
          placeholder="Name"
          className="border p-2 mb-4 w-full"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Description"
          className="border p-2 mb-4 w-full"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Beneficiary"
          className="border p-2 mb-4 w-full"
          value={beneficiary}
          onChange={(e) => setBeneficiary(e.target.value)}
          required
        />
        <input
          type="email"
          placeholder="Email"
          className="border p-2 mb-4 w-full"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Registration Number"
          className="border p-2 mb-4 w-full"
          value={registrationNumber}
          onChange={(e) => setRegistrationNumber(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Password"
          className="border p-2 mb-4 w-full"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Confirm Password"
          className="border p-2 mb-4 w-full"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Contact Number"
          className="border p-2 mb-4 w-full"
          value={contactInfo}
          onChange={(e) => setContactInfo(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Address"
          className="border p-2 mb-4 w-full"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
          required
        />
        <button type="submit" className="bg-blue-500 text-white py-2 px-4 rounded" disabled={loading}>
          {loading ? 'Registering...' : 'Register'}
        </button>
      </form>

      {isRegistered && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-4 rounded shadow-lg">
            <h3 className="text-xl font-bold mb-2">Registration Successful!</h3>
            <p>Please login.</p>
          </div>
        </div>
      )}
    </div>
  );
};

export default NGORegistration;
