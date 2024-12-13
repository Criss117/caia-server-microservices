"use client";
import Link from "next/link";

import CaiaTitle from "@/core/shared/components/ui/caia-title";
import { ROUTES } from "@/core/shared/lib/constants/routes";
import LoginForm from "../components/login/login-form";

const LoginScreen = () => {
  return (
    <section className="flex h-full">
      <div className="w-full xl:w-1/2 flex flex-col items-center justify-center">
        <header className="space-y-5 w-3/4 md:w-1/2">
          <CaiaTitle className="text-2xl" />
          <h2 className="font-semibold text-4xl">
            Inicia sesión
            <span className="block text-2xl">
              y empieza a crear conferencias
            </span>
          </h2>
        </header>
        <LoginForm />
        <footer className="mt-10">
          <Link href={ROUTES.SIGNUP}>No tienes una cuenta?</Link>
        </footer>
      </div>
      <div className="w-0 xl:w-1/2 bg-lightprimary-200 hidden xl:block"></div>
    </section>
  );
};

export default LoginScreen;
