import React from "react";

import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/core/shared/components/ui/card";
import { ROUTES } from "@/core/shared/lib/constants/routes";
import Link from "next/link";

const AccountCreated = () => {
  return (
    <div className="h-screen flex items-center justify-center">
      <Card className="bg-lightprimary-300 w-[50%] text-center">
        <CardHeader>
          <CardTitle className="text-2xl">Cuenta creada</CardTitle>
          <CardDescription>
            Se ha enviado un email de verificacion,
            <br /> revisa tu bandeja de entrada
          </CardDescription>
        </CardHeader>
        <CardContent>
          <Link href={ROUTES.LOGIN}>Volver al inicio sesion</Link>
        </CardContent>
      </Card>
    </div>
  );
};

export default AccountCreated;
