"use client";
import CreateConferenceScreen from "@/core/conferences/presentation/screens/create-conference.screen";
import { SessionProvider } from "next-auth/react";

const CreateConferencePage = () => {
  return (
    <SessionProvider>
      <CreateConferenceScreen />
    </SessionProvider>
  );
};

export default CreateConferencePage;
