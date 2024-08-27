import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios'
const DonorLogin = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault(); // prevent form submission reload
        setLoading(true);
        setError('');
    
        try {
            const res = await axios.post('http://localhost:8080/login/Doner', {
                email,
                password
            });
    
            localStorage.setItem('donerId', res.data.id);
    
            if (res.data.email === email && res.data.password === password) {
                navigate('/donor-campaigns');
            } else {
                setError('Invalid email or password');
            }
        } catch (error) {
            setError('Login failed. Please try again later.');
        } finally {
            setLoading(false);
        }
    };
    

    const handleSignupRedirect = () => {
        navigate('/donor-registration');
    };


    return (
        <div className="flex flex-col items-center justify-center h-1/2 bg-gray-100">
            <div className="w-full max-w-md p-6 bg-white rounded-lg shadow-md">
                <h2 className="text-2xl font-semibold text-center mb-6">Donor Login</h2>
                <form onSubmit={handleLogin}>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2">Email</label>
                        <input
                            className="w-full px-3 py-2 text-gray-700 border rounded-lg focus:outline-none focus:shadow-outline"
                            type="email"
                            placeholder="Enter your email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <div className="mb-6">
                        <label className="block text-gray-700 text-sm font-bold mb-2">Password</label>
                        <input
                            className="w-full px-3 py-2 text-gray-700 border rounded-lg focus:outline-none focus:shadow-outline"
                            type="password"
                            placeholder="Enter your password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    {error && <p className="text-red-500 text-sm mb-4">{error}</p>}
                    <button
                        className={`w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline ${loading ? 'opacity-50 cursor-not-allowed' : ''}`}
                        type="submit"
                        disabled={loading}
                    >
                        {loading ? 'Logging in...' : 'Login'}
                    </button>
                </form>
                <button
                    className="mt-4 text-blue-500 hover:text-blue-700 font-bold"
                    onClick={handleSignupRedirect}
                >
                    Donor Signup
                </button>
            </div>
        </div>
    );
};

export default DonorLogin;
