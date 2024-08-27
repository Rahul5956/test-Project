import React, { useState, useEffect } from 'react';
import axios from 'axios';
import NGOCampaignForm from './NGOCampaignForm';
import { useNavigate } from 'react-router-dom';
import campaignBackground from './assets/campaignBackground.jpg';

const NgoCampaigns = () => {
  const [campaigns, setCampaigns] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [editingCampaign, setEditingCampaign] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    fetchCampaigns();
  }, []);

  const fetchCampaigns = async () => {
    try {
      const response = await axios.get('http://localhost:8080/campaigns'); // Adjust the URL as needed
      setCampaigns(response.data);
    } catch (error) {
      console.error('Error fetching campaigns:', error);
    }
  };

  const handleAddCampaign = async (campaign) => {
    try {
      if (editingCampaign) {
        await axios.put(`http://localhost:8080/campaigns/${editingCampaign.id}`, campaign);
        setCampaigns(campaigns.map(c => c.id === editingCampaign.id ? campaign : c));
        setEditingCampaign(null);
      } else {
        const response = await axios.post('http://localhost:8080/campaigns/add', campaign);
        setCampaigns([...campaigns, response.data]);
      }
      setShowForm(false);
    } catch (error) {
      console.error('Error adding/updating campaign:', error);
    }
  };

  const handleEdit = (campaign) => {
    setEditingCampaign(campaign);
    setShowForm(true);
  };

  const handleDelete = async (id) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this campaign?");
    if (confirmDelete) {
      try {
        await axios.delete(`http://localhost:8080/campaigns/${id}`);
        setCampaigns(campaigns.filter(campaign => campaign.id !== id));
      } catch (error) {
        console.error('Error deleting campaign:', error);
      }
    }
  };

  const handleCancel = () => {
    setShowForm(false);
    setEditingCampaign(null);
  };

  const handleLogout = () => {
    const confirmLogout = window.confirm("Do you really want to log out?");
    if (confirmLogout) {
      // Perform any logout operations if needed (e.g., clearing tokens)
      navigate('/'); // Redirect to the landing page
    }
  };

  const handleAddItems = (campaign) => {
    navigate(`/ngo-donation/${campaign.id}`);
  };

  const handleViewFunds = (campaign) => {
    navigate(`/fund/${campaign.id}`);
  };

  return (
    <div
      className="p-6 min-h-screen bg-cover bg-center"
      style={{ backgroundImage: `url(${campaignBackground})` }}
    >
      <div className="flex justify-between mb-4">
        <button
          className="bg-green-500 text-white py-2 px-4 rounded"
          onClick={() => setShowForm(!showForm)}
        >
          {showForm ? 'Cancel' : 'Add Campaign'}
        </button>
        <button
          className="bg-red-500 text-white py-2 px-4 rounded"
          onClick={handleLogout}
        >
          Logout
        </button>
      </div>

      {showForm && (
        <NGOCampaignForm
          onAddCampaign={handleAddCampaign}
          onCancel={handleCancel}
          campaign={editingCampaign}
        />
      )}

      <div className="space-y-4">
        {campaigns.map((campaign) => (
          <div key={campaign.id} className="bg-white p-4 rounded-lg shadow-md">
            <h3 className="text-xl font-semibold">{campaign.title}</h3>
            <p>{campaign.description}</p>
            <p><strong>Start Date:</strong> {campaign.startDate}</p>
            <p><strong>End Date:</strong> {campaign.endDate}</p>
            <p><strong>Goal Amount:</strong> ₹{campaign.goalAmount}</p>
            <div className="flex space-x-2">
              <button
                className="bg-yellow-500 text-white py-1 px-3 rounded"
                onClick={() => handleEdit(campaign)}
              >
                Edit
              </button>
              <button
                className="bg-red-500 text-white py-1 px-3 rounded"
                onClick={() => handleDelete(campaign.id)}
              >
                Delete
              </button>
              <button
                className="bg-blue-500 text-white py-1 px-3 rounded"
                onClick={() => handleAddItems(campaign)}
              >
                Add Items
              </button>
              <button
                className="bg-purple-500 text-white py-1 px-3 rounded"
                onClick={() => handleViewFunds(campaign)}
              >
                View Funds
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default NgoCampaigns;
