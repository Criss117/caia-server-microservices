import { PropsWithChildren } from "react";

import LandingNavbar from "@/core/shared/components/landing/navbar";

const AccoutLayout = ({ children }: PropsWithChildren) => {
  return (
    <main className="h-screen">
      <LandingNavbar />
      {children}
    </main>
  );
};

export default AccoutLayout;
