"use client";;
import { use } from "react";

import SendPapersScreen from "@/core/papers/presentation/screens/send-papers.screen";
import { SessionProvider } from "next-auth/react";

interface Props {
  params: Promise<{
    slug: string;
  }>;
}

const SendPaperPage = (props: Props) => {
  const params = use(props.params);

  const {
    slug
  } = params;

  return (
    <SessionProvider>
      <SendPapersScreen slug={slug} />;
    </SessionProvider>
  );
};

export default SendPaperPage;
