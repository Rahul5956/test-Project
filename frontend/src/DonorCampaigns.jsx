import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const DonorCampaigns = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('All');
  const navigate = useNavigate();

  // Example campaign data (could be replaced with API data)
  const campaigns = [
    { title: 'Feed the Hungry', ngo: 'Helping Hands', category: 'Hunger', description: 'Providing meals for those in need.', goalAmount: 100000, currentAmount: 50000 },
    { title: 'Rescue Animals', ngo: 'Animal Aid', category: 'Animal', description: 'Rescuing stray animals and finding homes.', goalAmount: 50000, currentAmount: 20000 },
    { title: 'Disaster Relief Fund', ngo: 'Global Aid', category: 'Disaster', description: 'Helping victims of natural disasters.', goalAmount: 200000, currentAmount: 120000 },
    { title: 'Support Women', ngo: 'Women Empowerment', category: 'Women', description: 'Empowering women through education.', goalAmount: 150000, currentAmount: 75000 },
    { title: 'Children Education', ngo: 'Future Leaders', category: 'Children Education', description: 'Providing education to underprivileged children.', goalAmount: 180000, currentAmount: 90000 },
  ];

  const filteredCampaigns = campaigns.filter(campaign => {
    return (
      (selectedCategory === 'All' || campaign.category === selectedCategory) &&
      (campaign.title.toLowerCase().includes(searchTerm.toLowerCase()) || campaign.ngo.toLowerCase().includes(searchTerm.toLowerCase()))
    );
  });

  const handleCampaignClick = (campaign) => {
    navigate(`/donation/${campaign.title}`);
  };

  const handleLogout = () => {
    const confirmLogout = window.confirm("Do you really want to log out?");
    if (confirmLogout) {
      // You can add your logout logic here (e.g., clearing user data)
      navigate('/'); // Redirect to the landing page
    }
  };

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <div className="flex justify-between items-center mb-4">
        <input
          type="text"
          placeholder="Search by NGO or Title"
          className="w-full px-3 py-2 border rounded"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <button
          className="ml-4 bg-red-500 text-white py-2 px-4 rounded"
          onClick={handleLogout}
        >
          Logout
        </button>
      </div>

      <div className="mb-4">
        <nav className="flex flex-wrap gap-2">
          {['All', 'Animal', 'Hunger', 'Faith', 'Disaster', 'Children Education', 'Women'].map(category => (
            <button
              key={category}
              className={`px-4 py-2 rounded ${selectedCategory === category ? 'bg-blue-500 text-white' : 'bg-gray-200'}`}
              onClick={() => setSelectedCategory(category)}
            >
              {category}
            </button>
          ))}
        </nav>
      </div>

      <div className="space-y-4">
        {filteredCampaigns.length > 0 ? (
          filteredCampaigns.map((campaign, index) => (
            <div
              key={index}
              className="bg-white p-4 rounded-lg shadow-md cursor-pointer transition-transform hover:translate-y-[-0.5rem] hover:shadow-lg"
              onClick={() => handleCampaignClick(campaign)}
            >
              <h3 className="text-xl font-semibold">{campaign.title}</h3>
              <p>{campaign.description}</p>
              <p><strong>NGO:</strong> {campaign.ngo}</p>
              <p><strong>Category:</strong> {campaign.category}</p>
              <p><strong>Goal Amount:</strong> ₹{campaign.goalAmount.toLocaleString()}</p>
              <p><strong>Current Amount:</strong> ₹{campaign.currentAmount.toLocaleString()}</p>
            </div>
          ))
        ) : (
          <p>No campaigns found matching your criteria.</p>
        )}
      </div>
    </div>
  );
};

export default DonorCampaigns;
