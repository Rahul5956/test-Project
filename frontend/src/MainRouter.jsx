import React from 'react';
import { Routes, Route } from 'react-router-dom';
import LandingPage from './LandingPage';
import DonorLogin from './DonorLogin';
import DonorRegistration from './DonorRegistration';
import NGOLogin from './NGOLogin';
import NGORegistration from './NGORegistration';
import DonorCampaigns from './DonorCampaigns';
import NgoCampaigns from './NgoCampaigns';
import DonationPage from './DonationPage';
import NGODonation from './NGODonation';
import NGODonationForm from './NGODonationForm';
import Fund from './Fund';




function App() {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/donor-login" element={<DonorLogin />} />
      <Route path="/donor-registration" element={<DonorRegistration />} />
      <Route path="/ngo-login" element={<NGOLogin />} />
      <Route path="/ngo-registration" element={<NGORegistration />} />
      <Route path="/donor-campaigns" element={<DonorCampaigns />} />
      <Route path="/ngo-campaigns" element={<NgoCampaigns />} />
      <Route path="/donation/:title" element={<DonationPage />} />
      <Route path="/ngo-donation/:campaignTitle" element={<NGODonation />} />
      <Route path="/donation-form" element={<NGODonationForm />} />
      <Route path="/fund/:campaignTitle" element={<Fund />} />
    </Routes>
  );
}

export default App;
