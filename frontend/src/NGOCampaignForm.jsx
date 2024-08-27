import React, { useState, useEffect } from 'react';

const NGOCampaignForm = ({ onAddCampaign, onCancel, campaign }) => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [goalAmount, setGoalAmount] = useState('');

  useEffect(() => {
    if (campaign) {
      setTitle(campaign.title);
      setDescription(campaign.description);
      setStartDate(campaign.startDate);
      setEndDate(campaign.endDate);
      setGoalAmount(campaign.goalAmount);
    }
  }, [campaign]);

  const handleSubmit = (event) => {
    event.preventDefault();
    const newCampaign = {
      title,
      description,
      startDate,
      endDate,
      goalAmount,
    };

    onAddCampaign(newCampaign); // Trigger the callback function to add or update the campaign

    // Clear the form
    setTitle('');
    setDescription('');
    setStartDate('');
    setEndDate('');
    setGoalAmount('');
  };

  return (
    <div className="fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center">
      <div className="bg-white p-6 rounded-lg shadow-md w-full max-w-md">
        <h2 className="text-2xl font-semibold mb-4">
          {campaign ? 'Edit Campaign' : 'Add New Campaign'}
        </h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">Title</label>
            <input
              type="text"
              className="w-full px-3 py-2 border rounded"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">Description</label>
            <textarea
              className="w-full px-3 py-2 border rounded"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">Start Date</label>
            <input
              type="date"
              className="w-full px-3 py-2 border rounded"
              value={startDate}
              onChange={(e) => setStartDate(e.target.value)}
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">End Date</label>
            <input
              type="date"
              className="w-full px-3 py-2 border rounded"
              value={endDate}
              onChange={(e) => setEndDate(e.target.value)}
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">Goal Amount</label>
            <input
              type="number"
              className="w-full px-3 py-2 border rounded"
              value={goalAmount}
              onChange={(e) => setGoalAmount(e.target.value)}
              required
            />
          </div>
          <div className="flex justify-between">
            <button
              type="button"
              className="bg-red-500 text-white py-2 px-4 rounded"
              onClick={onCancel} // Call the onCancel prop function when Cancel is clicked
            >
              Cancel
            </button>
            <button
              type="submit"
              className="bg-blue-500 text-white py-2 px-4 rounded"
            >
              {campaign ? 'Update Campaign' : 'Add Campaign'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default NGOCampaignForm;
