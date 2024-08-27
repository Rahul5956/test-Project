import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

const Fund = () => {
  const { campaignTitle } = useParams();
  
  // Dummy data for the example
  const [funds, setFunds] = useState({
    totalAmount: 15000,
    lastUpdated: new Date().toLocaleString(),
  });

  // This useEffect simulates an update to the funds (dummy data update)
  useEffect(() => {
    const interval = setInterval(() => {
      setFunds((prevFunds) => ({
        ...prevFunds,
        totalAmount: prevFunds.totalAmount + Math.floor(Math.random() * 500), // Randomly add some amount
        lastUpdated: new Date().toLocaleString(),
      }));
    }, 5000); // Update every 5 seconds

    return () => clearInterval(interval);
  }, []);

  return (
    <div className="p-6">
      <h2 className="text-2xl font-semibold mb-4">Funds for {campaignTitle}</h2>
      <div className="bg-white p-4 rounded-lg shadow-md">
        <p className="text-xl">Total Amount Raised: ₹{funds.totalAmount}</p>
        <p className="text-sm text-gray-600">Last Updated: {funds.lastUpdated}</p>
      </div>
    </div>
  );
};

export default Fund;
