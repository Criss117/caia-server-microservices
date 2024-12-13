import type { Metadata } from "next";
import localFont from "next/font/local";
import "./globals.css";

const geistSans = localFont({
  src: "./fonts/GeistVF.woff",
  variable: "--font-geist-sans",
  weight: "100 900",
});

export const metadata: Metadata = {
  title: {
    default: "C.A.I.A",
    template: "%s | C.A.I.A",
  },
  description:
    "¿Qué esperas de esta conferencia? Sabemos que esperas mucho... tal vez demasiado. Sube tu paper si crees que hará la diferencia.",
  keywords: "conferencias, papers, gestión, calificación, sueños rotos",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={`${geistSans.className} antialiased bg-lightbg-200`}>
        {children}
      </body>
    </html>
  );
}
