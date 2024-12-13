import LandingNavbar from "@/core/shared/components/landing/navbar";
import FooterSection from "@/core/shared/components/landing/sections/footer-section";
import { Metadata } from "next";
import { PropsWithChildren } from "react";

export const metadata: Metadata = {};

const PublicLayout = ({ children }: PropsWithChildren) => {
  return (
    <main>
      <LandingNavbar search />
      {children}
      <FooterSection />
    </main>
  );
};

export default PublicLayout;
